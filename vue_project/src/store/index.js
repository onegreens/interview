import Vue from 'vue';
import Vuex from 'vuex';

import base from './modules/base';
import question from './modules/question';
import user from './modules/user';
import menu from './modules/menu';
import book from './modules/book';
import bookChapter from './modules/bookChapter';
import bookContent from './modules/bookContent';

Vue.use(Vuex);

const store = new Vuex.Store({
    modules: {
        base,
        book,
        bookContent,
        bookChapter,
        user,
        question,
        menu
    }
});

export default store