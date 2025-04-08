package dev.cat.musictheoryfx.config;

public enum FxmlView {

    HOME {

        @Override
        public String getFxmlPath() {
            return "/fxml/home.fxml";
        }
    },

    SCALES {

        @Override
        public String getFxmlPath() {
            return "/fxml/scales.fxml";
        }
    };

    public abstract String getFxmlPath();
}
