package del;

/*
@Component("result")
public class Result extends JLabel implements UiComponent, Colorizeble, Comparable<UiComponent> {

    private static final int FOCUSED_RATE = 6;
    private static final String DEFAULT_VIEW = "0.0 кг";
    private static final int SIZE_X = 125;
    private static final int SIZE_Y = 25;

    @Autowired
    private ColorChanger colorChanger;

    @Autowired
    @Qualifier("resultBehavior")
    private LabelBehavior labelBehavior;

    @PostConstruct
    private void afterPropertiesSet() {
        labelBehavior.setComponent(this);
        colorChanger.addColorizebleComponent(this);
    }

    public Result(int locationX, int locationY){
        super.setSize(SIZE_X, SIZE_Y);
        super.setVisible(true);
        super.setText(DEFAULT_VIEW);
        super.setHorizontalAlignment(SwingConstants.RIGHT);
        super.setLocation(locationX, locationY);
    }

    @Override
    public JComponent getComponentParent() {
        return this;
    }

    @Override
    public String getName() {
        return DEFAULT_VIEW;
    }

    @Override
    public boolean isTraversalPolicyFocused() {
        return true;
    }

    @Override
    public void acceptColorChanger(ColorChanger colorChanger) {
        colorChanger.changeServiceLabelColor(this);
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