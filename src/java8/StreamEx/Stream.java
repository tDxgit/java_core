package java8.StreamEx;

import java.util.*;

public class Stream {

	public static void main(String[] args) {
//		List<Customer> listCus = new ArrayList();
//		listCus.add(new Customer("An", "Ha Noi"));
//		listCus.add(new Customer("Nhien", "Hue"));
//		listCus.add(new Customer("Hoa", "HCM"));
//		listCus.add(new Customer("Binh", "HS TS"));
//		listCus.add(new Customer("You", "Ha Noi"));

		List<Transaction> listTran = new ArrayList();

		listTran.add(new Transaction(new Customer("An", "Ha Noi"), 2021, "Lavie", 200));
		listTran.add(new Transaction(new Customer("Nhien", "Hue"), 2020, "Gao", 900));
		listTran.add(new Transaction(new Customer("Thai", "Ha Noi"), 2018, "Gao", 300));
		listTran.add(new Transaction(new Customer("Binh", "Hai Duong"), 2021, "Chinsu", 10));
		listTran.add(new Transaction(new Customer("Nam", "Ha Noi"), 2019, "Banh", 120));
		listTran.add(new Transaction(new Customer("Thai", "Ha Noi"), 2019, "Gao", 500));
		listTran.add(new Transaction(new Customer("Mai", "Ha Noi"), 2021, "Sua", 1400));

		// giao dịch thực hiện trong năm 2021 và sắp xếp chúng theo số lượng tăng dần.
		listTran.stream().filter(s -> s.getYear().equals(2021))
				.sorted(new Comparator<Transaction>() {
					@Override
					public int compare(Transaction o1, Transaction o2) {
						
						return o1.getAmount()-o2.getAmount();
					}
				})
				.forEach(System.out::println);
		System.out.println();
		// In ra danh sách các địa chỉ của Customer (trùng thì loại)
		listTran.stream().map(s -> s.getCustomer().getCity()).distinct()
				.forEach(city -> System.out.print("city: " + city + ", "));
		System.out.println();
		System.out.println();
		
		// Xác định tất cả các khách hàng chung một địa chỉ nào đó, sort by name của  khách hàng và in ra.
			listTran.stream().filter(c->c.getCustomer().getCity().equals("Ha Noi"))
					.sorted((s1,s2) -> s1.getCustomer().getName().compareTo(s2.getCustomer().getName()))
					.forEach(System.out::println);
		System.out.println();
		
		// Sắp xếp tất cả khách hành theo tên và in ra.
		listTran.stream().map(s -> s.getCustomer().getName()).sorted((s1, s2) -> s1.compareTo(s2))
				.forEach(name -> System.out.print("Khach hang: " + name + ", "));
		System.out.println();
		
		// Kiểm tra xem có khách hàng ở địa chỉ nào đó hay không (y/n)?
		boolean get = listTran.stream().anyMatch(s -> s.getCustomer().getCity().equals("Ha Noi"));
		System.out.println("Co khach hang o dia chi tim kiem : " + get);
		System.out.println();
		
		// Xác định số lượng (amount) lớn nhất xuất hiện trong các giao dịch và in ra.
		Optional<Transaction> maxAmout = listTran.stream().max((a1, a2) -> a1.getAmount() - a2.getAmount());
		if (maxAmout.isPresent()) {
			Transaction getT = maxAmout.get();
			System.out.println("max Amout: " + getT.getAmount());
		}
		
		System.out.println();
		//Tính và in ra tổng số lượng trong các giao dịch của các khách hàng ở một địa chỉ nào đó
		int sum =listTran.stream().filter(c->c.getCustomer().getCity().equals("Ha Noi"))
				.mapToInt(s->s.getAmount()).sum();
		System.out.println("Tong Amout voi dia chi giao dich:" +sum);		
		
		// Xác định số lượng (amount) nho nhat xuất hiện trong các giao dịch và in ra.
		Optional<Transaction> minAmout = listTran.stream().min((a1, a2) -> a1.getAmount() - a2.getAmount());
		if (minAmout.isPresent()) {
			Transaction getT = minAmout.get();
			System.out.println(getT);
		}

	}

}
