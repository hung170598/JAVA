import java.util.Scanner;

public class CongNhan extends NhanSu {
	private XuongSX xuongSX;
	private String congViec;
	private int soNgayCong;
	private double luong;
	@Override
	public void input() {
		// TODO Auto-generated method stub
		super.input();
		Scanner sc = new Scanner(System.in);
		System.out.print("Nhap ten xuong SX: ");
		this.xuongSX.tenXuong = sc.nextLine();
		System.out.print("Nhap ten quan doc xuong SX: ");
		this.xuongSX.quanDoc = sc.nextLine();
		System.out.print("Nhap so cong nhan xuong SX: ");
		this.xuongSX.soCongNhan = sc.nextInt();
		System.out.print("Nhap cong viec: ");
		this.congViec = sc.nextLine();
		System.out.print("Nhap ten xuong SX: ");
		this.soNgayCong = sc.nextInt();
		this.tinhLuong();
	}
	@Override
	public void output() {
		// TODO Auto-generated method stub
		super.output();
		System.out.println("Ten xuong: " + this.xuongSX.tenXuong);
		System.out.println("Ten quan doc: " + this.xuongSX.quanDoc);
		System.out.println("So cong nhan: ");
	}
	
	public void tinhLuong() {
		
	}
}
