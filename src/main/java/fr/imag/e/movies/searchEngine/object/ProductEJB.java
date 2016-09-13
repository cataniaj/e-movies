package fr.imag.e.movies.searchEngine.object;

/*
 *	Author:      Jérémy Leyvraz
 *	Date:        11 sept. 2016
 */


public class ProductEJB {

	private long idProduct;
	private String idVideo;
	private String numberOfSeason;
	private String support;
	private String price;
	
	public ProductEJB(){
	}

	public long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(long idProduct) {
		this.idProduct = idProduct;
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

	public String getSupport() {
		return support;
	}

	public void setSupport(String support) {
		this.support = support;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	
	
}
