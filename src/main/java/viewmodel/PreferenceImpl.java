package viewmodel;

import org.springframework.stereotype.Service;
import viewcomponents.common.AppComponent;
import viewcontroller.Preference;

import java.io.*;
import java.util.List;
import java.util.Objects;
// TODO saving
@Service("preference")
class PreferenceImpl implements Serializable, Preference {

    private static final long serialVersionUID = 1L;

    private static final String SAVED_FILE_NAME = "save.calc";
    private transient static FileInputStream fileInputStream;
    private List<AppComponent> components;

    @Override
    public void saveComponents(List<AppComponent> savedComponents){
        this.components = savedComponents;
        save();
    }

    private void save(){
        try {
            savePreferenceToFile(new FileOutputStream(SAVED_FILE_NAME));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void savePreferenceToFile(FileOutputStream fileOutputStream) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(this);
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public boolean isSaved(){
        fileInputStream = readSavedFile();
        return fileInputStream != null;
    }

    private static FileInputStream readSavedFile() {
        try {
            return new FileInputStream(SAVED_FILE_NAME);
        } catch (FileNotFoundException e) {
            //create file
        }
        return null;
    }

    private static ObjectInputStream getObjectInputStream(){
        try {
            return new ObjectInputStream(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void readSavedComponents(){
        try {
            ObjectInputStream objectInputStream = getObjectInputStream();
            PreferenceImpl readObject = (PreferenceImpl) Objects.requireNonNull(objectInputStream).readObject();
            components = readObject.components;
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<AppComponent> loadComponents(){
        readSavedComponents();
        return components;
    }
}
