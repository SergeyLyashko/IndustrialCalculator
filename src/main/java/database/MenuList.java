package database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class MenuList {

    private static final String ASSORTMENT_HEADER = "Тип сортамента";
    private static final String TYPE_HEADER = "Тип профиля";
    private static final String NUMBER_HEADER = "№ профиля";

    private static final String ASSORTMENT_QUERY = "ProfileName";
    private static final String TYPE_QUERY = "ProfileTypeName";
    private static final String NUMBER_QUERY = "ProfileNumberName";

    // Sql запрос таблицы сортаментов(профилей)
    private static final String SQL_QUERY_PROFILES =
            "select ProfileName from Profiles";

    // Sql запрос таблицы типов профилей сортамента
    private static final String SQL_QUERY_TYPES =
            "select ProfileTypeName from Profiles, ProfileTypes "
                    + "where Profiles.Profile_ID = ProfileTypes.Profile_ID and "
                    + "Profiles.ProfileName = ?";

    // Sql запрос таблицы номеров профилей определенного типа
    private static final String SQL_QUERY_NUMBERS =
            "select ProfileNumberName from "
                    + "Profiles, ProfileTypes, ProfileNumbers "
                    + "where Profiles.Profile_ID = ProfileTypes.Profile_ID and "
                    + "ProfileTypes.ProfileType_ID = ProfileNumbers.ProfileType_ID and "
                    + "Profiles.ProfileName = ? and "
                    + "ProfileTypes.ProfileTypeName = ?";

    private final Data data;

    MenuList(Data data) {
        this.data = data;
    }

    private List<String> createDefaultMenu(String header){
        return Stream.of(header).collect(Collectors.toList());
    }

    List<String> receiveAssortmentList() {
        List<String> menu = createDefaultMenu(ASSORTMENT_HEADER);
        try {
            PreparedStatement preparedStatement = data.getPreparedStatement(SQL_QUERY_PROFILES);
            List<String> menuList = data.getMenuList(preparedStatement, ASSORTMENT_QUERY);
            menu.addAll(menuList);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return menu;
    }

    List<String> receiveTypeList(String assortment) {
        List<String> menu = createDefaultMenu(TYPE_HEADER);
        try {
            PreparedStatement preparedStatement = data.getPreparedStatement(SQL_QUERY_TYPES);
            data.initPreparedStatement(preparedStatement, 1, assortment);
            List<String> menuList = data.getMenuList(preparedStatement, TYPE_QUERY);
            menu.addAll(menuList);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return menu;
    }

    List<String> receiveNumberList(String assortment, String type) {
        List<String> menu = createDefaultMenu(NUMBER_HEADER);
        try {
            PreparedStatement preparedStatement = data.getPreparedStatement(SQL_QUERY_NUMBERS);
            data.initPreparedStatement(preparedStatement, 1, assortment);
            data.initPreparedStatement(preparedStatement, 2, type);
            List<String> menuList = data.getMenuList(preparedStatement, NUMBER_QUERY);
            menu.addAll(menuList);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return menu;
    }
}
