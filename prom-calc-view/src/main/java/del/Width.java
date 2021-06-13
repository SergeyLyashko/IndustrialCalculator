package del;

/*
@Component("width")
public class Width extends JFormattedTextField implements UiComponent, Comparable<UiComponent> {

    private static final int FOCUSED_RATE = 4;
    private static final String BOX_NAME = "введите ширину";
    private static final String TOOL_TIP_TEXT = "поле ввода ширины детали";
    private static final int WIDTH = 125;
    private static final int HEIGHT = 23;

    @Autowired
    @Qualifier("widthAction")
    private FieldsAction fieldsAction;

    @PostConstruct
    private void afterPropertiesSet() {
        fieldsAction.setComponent(this);
    }

    public Width(int locationX, int locationY){
        super.setSize(WIDTH, HEIGHT);
        super.setEditable(false);
        super.setText(BOX_NAME);
        super.setToolTipText(TOOL_TIP_TEXT);
        super.setHorizontalAlignment(JFormattedTextField.RIGHT);
        super.setLocation(locationX, locationY);
    }

    @Override
    public JComponent getComponentParent() {
        return this;
    }

    @Override
    public String getName() {
        return BOX_NAME;
    }

    @Override
    public boolean isTraversalPolicyFocused() {
        return true;
    }

    @Override
    public int getFocusRate() {
        return FOCUSED_RATE;
    }

    @Override
    public int compareTo(UiComponent o) {
        return this.getFocusRate() - o.getFocusRate();
    }
}
 */