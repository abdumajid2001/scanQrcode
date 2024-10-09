package abj.scanQrcode.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum Rank {

    LEYTENANT("Leytenant"), POLKOVNIK("Polkovnik");

    public final String value;

    Rank(String value) {
        this.value = value;
    }

}
