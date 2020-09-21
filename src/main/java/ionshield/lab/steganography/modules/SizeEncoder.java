package ionshield.lab.steganography.modules;

public class SizeEncoder implements SteganographyEncoder {
    private String header = "<!DOCTYPE HTML>\n" +
            "<html>\n" +
            "\n" +
            "<head>\n" +
            "<meta charset=\"UTF-8\">\n" +
            "<style>\n" +
            "html {\n" +
            "\tfont-family: Calibri, sans-serif;\n" +
            "\tbackground-color: #999;\n" +
            "}\n" +
            "body {\n" +
            "\tmargin: 0px auto 0px auto;\n" +
            "\tpadding: 20px;\n" +
            "\tbackground-color: #ddd;\n" +
            "\tborder: 1px solid #777;\n" +
            "\tmin-height: 300px;\n" +
            "\tborder-radius: 5px;\n" +
            "\tbox-shadow: 0px 0px 100px -70px black;\n" +
            "}\n" +
            "\n" +
            ".out {\n" +
            "\tfont-size: 16px !important;\n" +
            "}\n" +
            "\n" +
            ".out .large {\n" +
            "\tfont-size: 18px !important;\n" +
            "}\n" +
            "\n" +
            ".light-border {\n" +
            "\tborder-radius: 5px;\n" +
            "\tborder: 1px solid #888;\n" +
            "\tpadding: 1px 5px 1px 5px;\n" +
            "}\n" +
            "\n" +
            "div.box, fieldset, form.vis {\n" +
            "\tborder: 1px solid #888;\n" +
            "\tpadding: 5px 20px 5px 20px;\n" +
            "\tmargin: 10px 0px;\n" +
            "\tbackground-color: #ccc;\n" +
            "\tborder-radius: 10px;\n" +
            "\tbox-shadow: 0px 0px 50px -40px black inset;\n" +
            "}\n" +
            "\n" +
            "div.out {\n" +
            "\tborder: 1px solid #888;\n" +
            "\tpadding: 10px 20px 10px 20px;\n" +
            "\tmargin: 10px 100px 10px 100px;\n" +
            "\tbackground-color: #ccc;\n" +
            "\tborder-radius: 10px;\n" +
            "\tbox-shadow: 0px 0px 50px -45px black inset;\n" +
            "}\n" +
            "div.out {\n" +
            "\twhite-space:pre-wrap;\n" +
            "}\n" +
            "\n" +
            ".out p {\n" +
            "\tmargin: 5px 0px 5px 0px;\n" +
            "}\n" +
            "</style>\n" +
            "<title>Message</title>\n" +
            "</head>\n" +
            "\n" +
            "<body>\n" +
            "\n" +
            "<div class=\"out\">";
    private String footer = "</div>\n" +
            "</body>\n" +
            "</html>";
    private String tagOpen = "<span class = \"large\">";
    private String tagClose = "</span>";


    @Override
    public String encode(String base, String message) throws EncodingException {
        StringBuilder out = new StringBuilder();
        int curr = 0;
        out.append(header);
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            int found = base.indexOf(c, curr);
            if (found == -1) throw new EncodingException("Error: character '" + c + "' (" + i + ") not found, base: " + curr + "/" + base.length());
            out.append(base, curr, found);
            out.append(tagOpen);
            out.append(c);
            out.append(tagClose);
            curr = found + 1;
        }

        if (curr < base.length()) {
            out.append(base, curr, base.length());
        }

        out.append(footer);

        return out.toString();
    }
}
