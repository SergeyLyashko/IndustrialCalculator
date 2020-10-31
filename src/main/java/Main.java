import appview.AppView;

public class Main {
    public static void main(String[] args){

        AppView view = new AppView();
        view.create();
        view.savePreferencesAndExit();
    }
}
