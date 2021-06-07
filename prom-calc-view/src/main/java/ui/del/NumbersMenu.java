package ui.del;

/*
@Component("numbersMenu")
public class NumbersMenu extends JComboBox<String> implements MenuSelectable, Comparable<UiComponent> {

    private static final int FOCUSED_RATE = 3;
    private static final String NUMBER_HEADER = "№ профиля";
    private static final String TOOL_TIP_TEXT = "выбор номера профиля детали";
    private static final int WIDTH = 155;
    private static final int HEIGHT = 23;
    private String assortment = DEFAULT_MENU_VALUE;
    private String type = DEFAULT_MENU_VALUE;
    private boolean isConnect = true;

    private final HashMap<String, String> data;
    @Autowired
    private ViewController viewController;
    @Autowired
    private DataReceiver dataReceiver;


    public NumbersMenu(int locationX, int locationY){
        super.setSize(WIDTH, HEIGHT);
        super.setSelectedIndex(-1);
        super.setToolTipText(TOOL_TIP_TEXT);
        super.setLocation(locationX, locationY);
        this.data = new HashMap<>(3);
        data.put("assortment", assortment);
        data.put("type", type);
        data.put("number", DEFAULT_MENU_VALUE);
    }

    @PostConstruct
    private void afterPropertiesSet() {
        addActionListener();
        addClickListener();
        List<String> receivableMenu = receiveMenuList();
        createMenuModel(receivableMenu);
    }

    private void createMenuModel(List<String> receivableMenu){
        if(receivableMenu == null){
            receivableMenu = new LinkedList<>();
        }
        receivableMenu.add(0, NUMBER_HEADER);
        viewController.createMenu(receivableMenu, this);
    }

    private List<String> receiveMenuList() {
        try {
            return dataReceiver.receiveNumberMenu(assortment, type);
        } catch (SQLException exception) {
            isConnect = false;
        }
        return null;
    }

    @Override
    public void setMenuItems(String...menuItem) {
        if(menuItem.length != 0) {
            assortment = menuItem[0];
        }
        if(menuItem.length > 1){
            type = menuItem[1];
        }
        List<String> receivableMenuList = receiveMenuList();
        createMenuModel(receivableMenuList);
    }

    private void addActionListener(){
        super.addActionListener(event -> {
            String selectedItem = (String) super.getSelectedItem();
            if(selectedItem != null && !selectedItem.equals(NUMBER_HEADER)){
                Map<String, String> selectedItems = collectSelectedMenuItems(selectedItem);
                viewController.addSelectedItems(selectedItems);
            }
            if(isConnect){
                viewController.action();
            }
        });
    }

    private Map<String, String> collectSelectedMenuItems(String number){
        data.replace("assortment", assortment);
        data.replace("type", type);
        data.replace("number", number);
        return data;
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
}
*/