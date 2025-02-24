package LicenseFrame;

import javax.swing.*;
import java.awt.*;

public class licenseFrame extends JFrame {
    String licenseText =
            "## MIT License\n\n" +
                    "Copyright (c) 2025 Yang Seung-pil\n\n" +
                    "Permission is hereby granted, free of charge, to any person obtaining a copy\n" +
                    "of this software and associated documentation files (the \"Software\"), to deal\n" +
                    "in the Software without restriction, including without limitation the rights\n" +
                    "to use, copy, modify, merge, publish, distribute, sublicense, and/or sell\n" +
                    "copies of the Software, and to permit persons to whom the Software is\n" +
                    "furnished to do so, subject to the following conditions:\n\n" +
                    "The above copyright notice and this permission notice shall be included in\n" +
                    "all copies or substantial portions of the Software.\n\n" +
                    "THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR\n" +
                    "IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,\n" +
                    "FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE\n" +
                    "AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER\n" +
                    "LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,\n" +
                    "OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN\n" +
                    "THE SOFTWARE.\n\n" +
                    "---\n\n" +
                    "## Third-Party Licenses\n\n" +
                    "This software includes third-party libraries that are licensed separately.\n" +
                    "Please refer to their respective licenses below.\n\n" +
                    "### Gson Library (Apache License 2.0)\n\n" +
                    "This software uses the Gson library, which is licensed under the Apache License 2.0.\n" +
                    "The full license text is included below:\n\n" +
                    "**Apache License**\n" +
                    "Version 2.0, January 2004\n" +
                    "[http://www.apache.org/licenses/](http://www.apache.org/licenses/)\n\n" +
                    "**Copyright 2023 Google LLC**\n\n" +
                    "Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
                    "you may not use this file except in compliance with the License.\n" +
                    "You may obtain a copy of the License at:\n\n" +
                    "[http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0)\n\n" +
                    "Unless required by applicable law or agreed to in writing,\n" +
                    "software distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                    "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                    "See the License for the specific language governing permissions and limitations under the License.";

    JTextArea licenseTextArea = new JTextArea(20, 50);
    JScrollPane scrollPane;

    public licenseFrame() {
        super("License");

        licenseTextArea.setText(licenseText);
        licenseTextArea.setCaretPosition(0);
        licenseTextArea.setEditable(false);
        licenseTextArea.setLineWrap(true);
        licenseTextArea.setWrapStyleWord(true);
        licenseTextArea.setFont(new Font("Monospaced", Font.PLAIN, 15));

        scrollPane = new JScrollPane(licenseTextArea);
        scrollPane.setPreferredSize(new Dimension(750, 450));

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
