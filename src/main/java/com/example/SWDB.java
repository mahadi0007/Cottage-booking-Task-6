package com.example;

import com.example.model.BookingSuggestionResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.Dataset;
import org.apache.jena.query.DatasetFactory;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.riot.RDFDataMgr;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.example.query.Query3;

public class SWDB {
	
	static final int bookingIdStart = 1000;

	public ArrayList<BookingSuggestionResponse> searchForResult(String pathDB, RequestParams params) {
		System.out.println("------");
		System.out.println(params);
		//Calculate booking end date
		LocalDate bookingRequestStartDate = LocalDate.parse(params.getStartDate(), DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate bookingRequestEndDate = bookingRequestStartDate.plusDays(params.getDayCount());
		
		System.out.println("Do query...");

		Model model = RDFDataMgr.loadModel(pathDB);
		OntModelSpec ontModelSpec = OntModelSpec.OWL_DL_MEM;
		OntModel ontModel = ModelFactory.createOntologyModel(ontModelSpec, model);

		String queryString = new Query3().generateQuery(params);
		System.out.println("queryString: ---\n" + queryString);

		Dataset dataset = DatasetFactory.create(ontModel);
		Query q = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(q, dataset);
		ResultSet resultSet = qexec.execSelect();

		int tempBookingId = bookingIdStart;
		ArrayList<BookingSuggestionResponse> bookingList = new ArrayList<>();
		while (resultSet.hasNext()) {
			QuerySolution row = resultSet.next();
			Map<String, String> rowData = new HashMap<>();
			
			// Adding booker's name
			rowData.put("bookerName", params.getName());
			rowData.put("maxDayShifts", params.getMaxDayShifts());
			rowData.put("bookingNumber", String.valueOf(++tempBookingId));
			rowData.put("bookingStartDate", params.getStartDate());
			rowData.put("bookingEndDate", bookingRequestEndDate.toString());

			row.varNames().forEachRemaining(varName -> {
				
				RDFNode node = row.get(varName);
				System.out.println("->" + varName + "------>" + (node != null ? node.toString() : "null"));
//				rowData.put(varName, (node != null ? node.toString() : "null"));
				rowData.put(varName, (node.toString()));
			});
			
//			rowData.forEach((key, value) -> System.out.println(key + ":------------ " + value));

			bookingList.add(new BookingSuggestionResponse(rowData));
		}

		for (BookingSuggestionResponse booking : bookingList) {
			System.out.println("9-------9----------99" + booking.getisAvailableFrom());
			System.out.println("5-------------5555" + booking.getBookingStartDate());
		}
		return bookingList;
	}

	public BookingSuggestionResponse getResult() {
		return new BookingSuggestionResponse("Alice Johnson", // name of the booker
				"BK-20241029-67890", // booking number
				"456 Mountain View Drive", // address of the cottage
				"http://example.com/mountain.jpg", // image of the cottage
				8, // actual number of places
				4, // actual number of bedrooms
				100, // distance to lake in meters
				"Evergreen City", // nearest city
				30, // distance to nearest city in km
				"2024-12-15", // booking start date
				"2024-12-25", // booking end date
				"2024-12-12" // available date
		);
	}
}