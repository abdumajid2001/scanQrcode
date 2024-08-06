package abj.scanQrcode.enums;

public enum Language {

    UZ("uz"), EN("en"), RU("ru");

    public final String value;

    Language(String language) {
        this.value = language;
    }

    public static Language getValue(String label) {
        for (Language e : values()) {
            if (e.value.equals(label)) {
                return e;
            }
        }

        return null;
    }

}
