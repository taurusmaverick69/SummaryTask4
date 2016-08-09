package ua.nure.lyubimtsev.SummaryTask4;

public class Redirect {

    private String URL;
    private ForwardingType forwardingType;

    public Redirect() {
    }

    public Redirect(String URL, ForwardingType forwardingType) {
        this.URL = URL;
        this.forwardingType = forwardingType;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public ForwardingType getForwardingType() {
        return forwardingType;
    }

    public void setForwardingType(ForwardingType forwardingType) {
        this.forwardingType = forwardingType;
    }


    @Override
    public String toString() {
        return "Redirect{" +
                "forwardingType=" + forwardingType +
                ", URL='" + URL + '\'' +
                '}';
    }
}
