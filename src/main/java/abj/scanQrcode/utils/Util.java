package abj.scanQrcode.utils;

import java.util.Objects;

public class Util {

    public static boolean isNullFields(String... fields) {
        if (Objects.isNull(fields)) {
            return true;
        }

        for (String value : fields) {
            if (Objects.isNull(value) || value.isEmpty()) {
                return true;
            }
        }

        return false;
    }

}
