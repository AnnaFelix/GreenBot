package lu.uni.bicslab.greenbot.android.ui.activity.feedback;

public class ProductToReview {
    String timestamp;
    String product_ean;

    public ProductToReview(String timestamp, String product_ean) {
        this.timestamp = timestamp;
        this.product_ean = product_ean;
    }


    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getProduct_ean() {
        return product_ean;
    }

    public void setProduct_ean(String product_ean) {
        this.product_ean = product_ean;
    }
}
