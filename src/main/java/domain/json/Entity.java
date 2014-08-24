package domain.json;

public class Entity {

    private String _id;
    private String key;
    private String name;
    private String fullName;
    private String type;
    private String country;
    private GeoPosition geo_position;
    private String locationId;
    private String iata_airport_code;
    private String inEurope;
    private String countryCode;
    private String coreCountry;
    private String distance;

    public String get_id() {
        return _id;
    }

    public void set_id(final String _id) {
        this._id = _id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(final String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public GeoPosition getGeo_position() {
        return geo_position;
    }

    public void setGeo_position(final GeoPosition geo_position) {
        this.geo_position = geo_position;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(final String locationId) {
        this.locationId = locationId;
    }

    public String getIata_airport_code() {
        return iata_airport_code;
    }

    public void setIata_airport_code(final String iata_airport_code) {
        this.iata_airport_code = iata_airport_code;
    }

    public String getInEurope() {
        return inEurope;
    }

    public void setInEurope(final String inEurope) {
        this.inEurope = inEurope;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(final String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCoreCountry() {
        return coreCountry;
    }

    public void setCoreCountry(final String coreCountry) {
        this.coreCountry = coreCountry;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(final String distance) {
        this.distance = distance;
    }
}
