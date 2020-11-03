import appview.ApplicationView;

public class Main {
    public static void main(String[] args){

        ApplicationView view = new ApplicationView();
        view.create();
        view.savePreferencesAndExit();
    }
}
