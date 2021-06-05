package ui.calculator;

/*
@Component("typesMenu")
public class TypesMenu extends JComboBox<String> implements MenuSelectable, Comparable<UiComponent> {

    private static final int FOCUSED_RATE = 2;
    private static final String TYPE_HEADER = "Тип профиля";
    private static final String TOOL_TIP_TEXT = "выбор типа профиля детали";
    private static final int WIDTH = 155;
    private static final int HEIGHT = 23;
    private String assortment = DEFAULT_MENU_VALUE;
    private boolean isConnect = true;

    @Autowired
    private ViewController viewController;
    @Autowired
    private DataReceiver dataReceiver;


    public TypesMenu(int locationX, int locationY){
        super.setSize(WIDTH, HEIGHT);
        super.setSelectedIndex(-1);
        super.setToolTipText(TOOL_TIP_TEXT);
        super.setLocation(locationX, locationY);
    }

    @PostConstruct
    private void afterPropertiesSet() {
        addActionListener();
        addClickListener();
        List<String> receivableMenu = receiveMenu();
        createMenuModel(receivableMenu);
    }

    private List<String> receiveMenu() {
        try {
            return dataReceiver.receiveTypeMenu(assortment);
        } catch (SQLException exception) {
            isConnect = false;
        }
        return null;
    }

    @Override
    public void setMenuItems(String...menuItem) {
        if(menuItem.length != 0){
            assortment = menuItem[0];
        }
        List<String> receivableMenuList = receiveMenu();
        createMenuModel(receivableMenuList);
    }

    private void createMenuModel(List<String> receivableMenu){
        if(receivableMenu == null){
            receivableMenu = new LinkedList<>();
        }
        receivableMenu.add(0, TYPE_HEADER);
        viewController.createMenu(receivableMenu, this);
    }

    private void addActionListener(){
        super.addActionListener(event -> {
            String selectedItem = (String) super.getSelectedItem();
            viewController.fieldsOff();
            if(selectedItem.equalsIgnoreCase("резиновая пластина") ||
                    selectedItem.equalsIgnoreCase("тонколистовая") ||
                    selectedItem.equalsIgnoreCase("толстолистовая") ||
                    selectedItem.equalsIgnoreCase("рифленая(ромб)")){
                viewController.widthOn();
            }
        });
    }

    private void addClickListener(){
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
    public void addMenuSelectListener(MenuSelectable listener){
        super.addActionListener(event -> {
            String selectedItem = (String) super.getSelectedItem();
            listener.setMenuItems(assortment, selectedItem);
        });
    }

    @Override
    public JComboBox<String> getComponentParent() {
        return this;
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
 }*/
