package dev.cat.musictheoryfx.config;

public enum FxmlView {


    LOGIN {

        @Override
        public String getFxmlPath() {
            return "/fxml/login.fxml";
        }
    },

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
    },

    SCALES_THEORY {

        @Override
        public String getFxmlPath() {
            return "/fxml/scales-theory.fxml";
        }
    };

    public abstract String getFxmlPath();
}
