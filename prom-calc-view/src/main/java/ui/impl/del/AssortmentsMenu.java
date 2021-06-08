package ui.impl.del;

/*
@Component
public class AssortmentsMenu extends JComboBox<String> implements MenuSelectable, Comparable<UiComponent> {

    private static final int FOCUSED_RATE = 1;
    private static final String ASSORTMENT_HEADER = "Тип сортамента";
    private static final String TOOL_TIP_TEXT = "выбор сортамента детали";
    private static final int WIDTH = 155;
    private static final int HEIGHT = 23;
    private boolean isConnect = true;

    @Autowired
    private ViewController viewController;
    @Autowired
    private DataReceiver dataReceiver;


    public AssortmentsMenu(int locationX, int locationY){
        super.setSize(WIDTH, HEIGHT);
        super.setSelectedIndex(-1);
        super.setToolTipText(TOOL_TIP_TEXT);
        super.setLocation(locationX, locationY);
    }

    @PostConstruct
    private void afterPropertiesSet() {
        addListener(viewController);
        clickListener();
        List<String> receivableMenu = receiveMenu();
        createMenuModel(receivableMenu);
    }

    private void addListener(ViewController viewController){
        super.addActionListener(event -> viewController.fieldsOff());
    }

    @Override
    public void addMenuSelectListener(MenuSelectable listener){
        super.addActionListener(event -> {
            String selectedItem = (String) super.getSelectedItem();
            listener.setMenuItems(selectedItem, DEFAULT_MENU_VALUE);
        });
    }

    private void clickListener(){
        super.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                if(!isConnect){
                    viewController.setMessage(NOT_DATABASE_MESSAGE, true);
                    viewController.setResult(MenuSelectable.ERROR, true);
                }
            }
        });
    }

    @Override
    public void setMenuItems(String...menuItem) {
        List<String> receivableMenu = receiveMenu();
        createMenuModel(receivableMenu);
    }

    private List<String> receiveMenu() {
        try {
            return dataReceiver.receiveAssortmentMenu();
        } catch (SQLException exception) {
            isConnect = false;
        }
        return null;
    }

    private void createMenuModel(List<String> receivableMenu){
        if(receivableMenu == null){
            receivableMenu = new LinkedList<>();
        }
        receivableMenu.add(0, ASSORTMENT_HEADER);
        viewController.createMenu(receivableMenu, this);
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
    public JComboBox<String> getComponentParent() {
        return this;
    }

    @Override
    public int compareTo(UiComponent o) {
        return this.getFocusRate() - o.getFocusRate();
    }
}*/
