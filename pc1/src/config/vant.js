import Vue from 'vue'
import {
    Button, Tabbar, TabbarItem, Toast, 
    AddressEdit, NavBar, Cell, CellGroup, 
    Dialog, Image, Icon, Grid, GridItem, 
    Tab, Tabs, Popup, RadioGroup, Radio, AddressList,
    SubmitBar, Checkbox, Field, DatetimePicker, Swipe, SwipeItem,
    Tag, TreeSelect, Loading, Card, GoodsAction, GoodsActionIcon, 
    GoodsActionButton,Row, Col, Sku,Lazyload,Stepper,PasswordInput,
    NumberKeyboard,ActionSheet,Uploader,Rate,CountDown,
    Search,DropdownMenu, DropdownItem

} from 'vant'
Vue.use(Button).use(Tabbar)
    .use(TabbarItem).use(Toast)
    .use(AddressEdit).use(NavBar)
    .use(Cell).use(CellGroup).use(Dialog)
    .use(Image).use(Icon).use(Grid).use(GridItem)
    .use(Tab).use(Tabs).use(Popup).use(Radio).use(RadioGroup)
    .use(AddressList).use(SubmitBar).use(Checkbox).use(Field)
    .use(DatetimePicker).use(Swipe).use(SwipeItem).use(Tag)
    .use(TreeSelect).use(Loading).use(Card).use(GoodsAction)
    .use(GoodsActionIcon).use(GoodsActionButton).use(Row).use(Col)
    .use(Sku).use(Lazyload).use(Stepper).use(PasswordInput).use(NumberKeyboard)
    .use(ActionSheet).use(Uploader).use(Rate).use(CountDown).use(Search)
    .use(DropdownMenu).use(DropdownItem)