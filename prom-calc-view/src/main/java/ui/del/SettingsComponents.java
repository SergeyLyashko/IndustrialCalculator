package ui.del;

/*
@Service("settingsComponents")
public class SettingsComponents implements CalculatorComponents {

    private List<UiComponent> components;

    @Autowired
    private ViewController viewController;

    @Autowired
    @Qualifier("colorThemeBox")
    private UiComponent colorThemeCheckBox;

    @Autowired
    @Qualifier("toolTipsBox")
    private UiComponent toolTipsCheckBox;

    @PostConstruct
    private void afterPropertiesSet() throws Exception {
        List<UiComponent> saved = viewController.loadComponents();
        if(saved == null){
            components = new ArrayList<>();
            components.add(colorThemeCheckBox);
            components.add(toolTipsCheckBox);
        }else {
            components = saved;
        }
    }

    @Override
    public List<UiComponent> getComponents(){
        return components;
    }

    @PreDestroy
    private void winClose() {
        System.out.println("destroy");
        // TODO написать сохранение настроек
        //viewController.savedPreference(components);
    }
}
*/