package comboboxes;

import appcomponents.SwingComponent;

import java.awt.*;
import java.util.List;

public interface SelectableComboBox extends SwingComponent {

    default List<SwingComponent> getComponents(){ return null; }

    default LayoutManager getLayout(){ return  null; }

    default String getBorderLayout() { return  null; }

}
