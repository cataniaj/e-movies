package fr.imag.searchEngine.object;

/**
 * Représente une video
 * @author Jerem
 *
 */
public class VideoEJB {

	private String idVideo;
	private String numberOfSeason;
	private String title;
	private String year;
	private String numberOfDvd;
	private String numberOfBluray;
	private String numberOfSales;
	
	public VideoEJB(){
		
	}

	public String getIdVideo() {
		return idVideo;
	}

	public void setIdVideo(String idVideo) {
		this.idVideo = idVideo;
	}

	public String getNumberOfSeason() {
		return numberOfSeason;
	}

	public void setNumberOfSeason(String numberOfSeason) {
		this.numberOfSeason = numberOfSeason;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getNumberOfDvd() {
		return numberOfDvd;
	}

	public void setNumberOfDvd(String numberOfDvd) {
		this.numberOfDvd = numberOfDvd;
	}

	public String getNumberOfBluray() {
		return numberOfBluray;
	}

	public void setNumberOfBluray(String numberOfBluray) {
		this.numberOfBluray = numberOfBluray;
	}

	public String getNumberOfSales() {
		return numberOfSales;
	}

	public void setNumberOfSales(String numberOfSales) {
		this.numberOfSales = numberOfSales;
	}
	
	public void print(){
		System.out.println(idVideo);
		System.out.println(numberOfSeason);
		System.out.println(title);
		System.out.println(year);
		System.out.println(numberOfDvd);
		System.out.println(numberOfBluray);
		System.out.println(numberOfSales);
	}
	
}
