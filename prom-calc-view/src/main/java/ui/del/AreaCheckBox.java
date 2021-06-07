package ui.del;

/*
@Component
public class AreaCheckBox extends JCheckBox implements UiComponent, Host {

    private static final String TOOL_TIP_TEXT = "расчет массы детали по задаваемой площади детали";
    private static final String BOX_NAME = "сложный периметр";
    private static final int WIDTH = 320;
    private static final int HEIGHT = 20;

    @Autowired
    private ViewController viewController;
    @Autowired
    private Visitor colorVisitor;

    public AreaCheckBox(int locationX, int locationY){
        super.setSelected(false);
        super.setSize(WIDTH, HEIGHT);
        super.setText(BOX_NAME);
        super.setToolTipText(TOOL_TIP_TEXT);
        super.setLocation(locationX, locationY);
    }

    @PostConstruct
    private void afterPropertiesSet() {
        setFont();
        colorVisitor.addHost(this);
        addItemListener(viewController);
    }

    private void setFont(){
        Font deriveFont = super.getFont().deriveFont(10f);
        super.setFont(deriveFont);
    }

    private void addItemListener(ViewController viewController){
        super.addItemListener(event ->
                viewController.areaCheckBoxState(event.getStateChange() == ItemEvent.SELECTED));
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visitComponent(this);
    }

    @Override
    public JComponent getComponentParent() {
        return this;
    }
}
*/