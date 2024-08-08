package abj.scanQrcode.enums;

import lombok.Getter;

@Getter
public enum Rank {

    LEYTENANT("Leytenant"), POLKOVNIK("Polkovnik");

    public final String value;

    Rank(String value) {
        this.value = value;
    }

}
