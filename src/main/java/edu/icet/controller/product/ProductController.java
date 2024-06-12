package edu.icet.controller.product;

import edu.icet.bo.BoFactory;
import edu.icet.bo.ProductBo;
import edu.icet.dto.Product;
import edu.icet.dto.Supplier;
import edu.icet.util.BoType;
import javafx.scene.control.Alert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductController {

    private static ProductController instance;

    private ProductBo productBo = BoFactory.getInstance().getBo(BoType.PRODUCT);

    private ProductController(){}

    public static ProductController getInstance(){
        if(instance==null){
            return instance = new ProductController();
        }
        return instance;
    }

    public String generateProductId(){
        long count = productBo.getCount();
        System.out.println(count);
        if (count == 0) {
            return "P001";
        }
        String last = productBo.getLast();
        Pattern pattern = Pattern.compile("[A-Za-z](\\d+)");
        Matcher matcher = pattern.matcher(last);
        if (matcher.find()) {
            int number = Integer.parseInt(matcher.group(1));
            number++;
            System.out.println(number);
            return String.format("P%03d", number);
        } else {
            new Alert(Alert.AlertType.WARNING,"hello").show();
        }
        return null;
    }

    public Product searchProduct(String id){
        return productBo.getById(id);
    }
}
