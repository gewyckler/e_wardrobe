package pl.javagda25.ewardrobe.model;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Lob;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

@Getter
public enum SeasonName {
    ALL_YEAR(1, 365, readsByteFromFile("D:/java/projects/projekt koncowy/ewardrobe/src/main/lallyears.jpg")),
    SPRING(80, 171, readsByteFromFile("../lspring.jpg")),
    SUMMER(172, 263, readsByteFromFile("../lsummer.jpg")),
    AUTUMN(264, 361, readsByteFromFile("../lautumn.jpg")),
    WINTER(362, 79, readsByteFromFile("../lwinter.png")),
    SPRING_SUMMER(80, 263, readsByteFromFile("")),
    AUTUMN_WINTER(264, 79, readsByteFromFile(""));

    private Integer startDay;
    private Integer endDay;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] seasonLogo;

    SeasonName(Integer startDay, Integer endDay, byte[] seasonLogo) {
        this.startDay = startDay;
        this.endDay = endDay;
        this.seasonLogo = seasonLogo;
    }

    public String convertBinImageToString() {
        if (seasonLogo != null && seasonLogo.length > 0) {
            return Base64.getEncoder().encodeToString(seasonLogo);
        }
        return "";
    }

    public static byte[] readsByteFromFile(String imgPath) {
        File file = new File(String.valueOf(Paths.get(imgPath)));
        byte[] byteArray = new byte[(int) file.length()];
        if (file.exists()) {
            System.out.println("ISTNIEJE!!");
            System.out.println(imgPath);
            byteArray = new byte[(int) file.length()];
            try {
                return byteArray = Files.readAllBytes(Paths.get(imgPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return byteArray;
    }
}
