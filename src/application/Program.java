package application;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entites.Product;

public class Program {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
  
	  Locale.setDefault(Locale.US);
	  Scanner sc = new Scanner(System.in);
	  
	  List<Product> list = new ArrayList<>();
	  
	  System.out.println("Entre com o caminho do arquivo");
	  String caminho = sc.nextLine();
	  
	  File file = new File(caminho);
	  String pasta = file.getParent();
	  
	  boolean success = new File(pasta+"\\out").mkdir();
	  
	  String novoarquivo = pasta+"\\out\\sumary.csv";
	  
	  try(BufferedReader br = new BufferedReader(new FileReader(caminho))){
		  
		  String itemcsv = br.readLine();
		  
		  while(itemcsv != null) {
			  String[] fields = itemcsv.split(",");
			  String name = fields[0];
			  Double price = Double.parseDouble(fields[1]);
			  Integer quantity = Integer.parseInt(fields[2]); 
			  
			  list.add(new Product(name,price,quantity));
			  
			  itemcsv = br.readLine();
			  
			  
		  }try (BufferedWriter bw = new BufferedWriter(new FileWriter(novoarquivo)))
		  {
			  for(Product item : list) {
				  
				  bw.write(item.getName()+","+ String.format("%.2f",item.total()));
				  bw.newLine();
			  }
			  System.out.println("Created with sucefully");
		  }
		  catch(IOException e) {
			  
			  System.out.println("Error: "+ e.getMessage());
		  }
		  
	  }catch(IOException e)
	  {
		  System.out.println(e.getMessage());
	  }
	  
	  
	   
		
		
		
		
		sc.close();
		
	}

}
