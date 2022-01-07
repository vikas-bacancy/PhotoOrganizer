package com.movemedical.hiring;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PhotoOrganizer {

	/*
	*  Converting array of raw details of photos to List of Photo Entity
	* */
	private List<Photo> getListOfPhotos(String[] data) {

		List<Photo> photoList = new ArrayList<>(data.length);

		for(String photo : data) {
			// Separating the given details of the photo
			String[] details = photo.split(", ");

			// converting the mentioned date in String to Date object
			Date date;
			try {
				date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(details[2]);
			} catch (ParseException ex) {
				date = new Date(details[2]);
			}

			// Fetching the extension from the given name in the input
			String extension = "." + details[0].split("\\.")[1];

			//Adding the details as Photo Entity
			photoList.add(new Photo(date, details[0], details[1], extension));
		}

		return photoList;
	}

	/*
	 *  Return the count of digits in a number
	 * */
	private int getDigitsOfNumber(int number) {
		int count = 0;

		while (number > 0) {
			count++;
			number /= 10;
		}

		return count;
	}

	/*
	 *  Return old name of the photo to new name mapping
	 * */
	private HashMap<String,String> getNewNameOfPhotos(Map<String,TreeMap<Date,Photo>> photosGroupedByCity) {

		HashMap<String,String> newNameMapping = new HashMap<>();

		for(String city : photosGroupedByCity.keySet()) {

			int numberOfPhotos = photosGroupedByCity.get(city).size();

			// Highest number in this group
			int highestSequence = getDigitsOfNumber(numberOfPhotos);

			// List of Photos in this group
			List<Photo> images = new ArrayList<>(photosGroupedByCity.get(city).values());

			for(int i=0; i<numberOfPhotos; i++) {

				Photo photo = images.get(i);

				// digits in the sequence number of this photo
				int digits = getDigitsOfNumber(i+1);

				// String Builder object to create the name
				StringBuilder nameBuilder = new StringBuilder();

				nameBuilder.append(photo.getCity());

				// Logic to prepend the zeros in the sequence number
				if(digits < highestSequence) {
					for(int j=0; j < (highestSequence - digits); j++) {
						nameBuilder.append("0");
					}
				}
				nameBuilder.append(i + 1).append(photo.getExtension());

				photo.setNewName(nameBuilder.toString());

				newNameMapping.put(photo.getName(), photo.getNewName());
			}
		}
		return newNameMapping;
	}

	public String organizeMyPhotos(String photoData) {

		StringBuilder result = new StringBuilder();

		// Separating data of each photo
		String[] data = photoData.split("\\R");

		List<Photo> photos;
		HashMap<String,String> newNameMapping;
		Map<String,TreeMap<Date,Photo>> photosGroupedByCity = new HashMap<>();

		// Converting array of raw details of photos to List of Photo Entity
		photos = getListOfPhotos(data);

		// Grouping photos by city
		for(Photo photo : photos) {

			TreeMap<Date,Photo> cityMap;

			if(photosGroupedByCity.containsKey(photo.getCity())) {
				cityMap = photosGroupedByCity.get(photo.getCity());
			} else {
				cityMap = new TreeMap<>();
			}
			cityMap.put(photo.getDate(), photo);
			photosGroupedByCity.put(photo.getCity(), cityMap);
		}

		// Getting the Old name to New name Mapping of all Photos
		newNameMapping = getNewNameOfPhotos(photosGroupedByCity);

		// Preparing the result
		for(int i=0; i<photos.size(); i++) {
			Photo photo = photos.get(i);

			result.append(newNameMapping.get(photo.getName()));

			if(i != photos.size()-1) {
				result.append("\n");
			}
		}

		return result.toString();
	}
}
