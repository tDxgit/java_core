package java8.StreamEx;

public class Transaction {
	private Customer trader;
	private Integer year;// Năm thực hiện giao dịch
	private String prodName;// Tên sản phẩm
	private Integer amount; // Số lượng

	public Transaction(Customer t, int y, String name, int v) {
		trader = t;
		year = y;
		prodName = name;
		amount = v;
	}

	public Customer getCustomer() {
		return trader;
	}

	public void setCustomer(Customer trader) {
		this.trader = trader;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

@Override
	public String toString(){
	return trader.toString()+" Transaction (Year:" + year
		+ ", ProdName: " + getProdName() + ", Amount: " + getAmount() + ")";
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
}
