package com.rajuprctical.Model;

/**
 * Created by Shailesh on 14/03/18.
 */

public class SubProductModel {


    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getCurrency_symbol() {
        return currency_symbol;
    }

    public void setCurrency_symbol(String currency_symbol) {
        this.currency_symbol = currency_symbol;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getParent_category_id() {
        return parent_category_id;
    }

    public void setParent_category_id(String parent_category_id) {
        this.parent_category_id = parent_category_id;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getOn_sale() {
        return on_sale;
    }

    public void setOn_sale(String on_sale) {
        this.on_sale = on_sale;
    }

    public String getSale_price() {
        return sale_price;
    }

    public void setSale_price(String sale_price) {
        this.sale_price = sale_price;
    }

    public String getRegular_price() {
        return regular_price;
    }

    public void setRegular_price(String regular_price) {
        this.regular_price = regular_price;
    }

    public String getTax_product_with_price_regula() {
        return tax_product_with_price_regula;
    }

    public void setTax_product_with_price_regula(String tax_product_with_price_regula) {
        this.tax_product_with_price_regula = tax_product_with_price_regula;
    }

    public String getTax_product_with_price_sale() {
        return tax_product_with_price_sale;
    }

    public void setTax_product_with_price_sale(String tax_product_with_price_sale) {
        this.tax_product_with_price_sale = tax_product_with_price_sale;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    String product_id,title,qty,currency_symbol,description,status,rating,parent_category_id,suffix,on_sale,sale_price,regular_price,tax_product_with_price_regula,tax_product_with_price_sale,image;
}
