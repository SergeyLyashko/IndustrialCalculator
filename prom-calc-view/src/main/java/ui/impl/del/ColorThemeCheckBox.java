package ui.impl.del;

/*
@Component("colorThemeBox")
public class ColorThemeCheckBox extends JCheckBox implements UiComponent, Host {

    @Autowired
    private transient Visitor colorVisitor;

    private static final String BOX_NAME = "темная тема оформления";
    private static final String TOOL_TIP_TEXT = "включить/отключить темную тему приложения";
    private static final int WIDTH = 320;
    private static final int HEIGHT = 20;

    public ColorThemeCheckBox(int locationX, int locationY){
        super.setSelected(true);
        super.setSize(WIDTH, HEIGHT);
        super.setText(BOX_NAME);
        super.setToolTipText(TOOL_TIP_TEXT);
        super.setLocation(locationX, locationY);
    }

    @PostConstruct
    private void afterPropertiesSet() {
        colorVisitor.addHost(this);
        addItemListener();
        checkBoxStateChecked();
    }

    private void checkBoxStateChecked(){
        if(super.isSelected()){
            colorVisitor.activate();
        }else {
            colorVisitor.deactivate();
        }
    }

    private void addItemListener(){
        super.addItemListener(event -> {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                colorVisitor.activate();
            } else {
                colorVisitor.deactivate();
            }
            colorVisitor.raid();
        });
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
