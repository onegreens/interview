import Vue from 'vue';
import Vuex from 'vuex';
import question from './modules/question';
import user from './modules/user';
import menu from './modules/menu';

Vue.use(Vuex);

const store = new Vuex.Store({
    modules: {
        user,
        question,
        menu
    }
});

export default store