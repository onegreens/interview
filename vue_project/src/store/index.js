import Vue from 'vue';
import Vuex from 'vuex';
import question from './modules/question';
import user from './modules/user';
import menu from './modules/menu';
import book from './modules/book';

Vue.use(Vuex);

const store = new Vuex.Store({
    modules: {
        book,
        user,
        question,
        menu
    }
});

export default store