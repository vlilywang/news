'use strict';

/**
 * @param {Egg.Application} app - egg application
 */
module.exports = app => {
    const { router, controller } = app;
    router.get('/', controller.home.index);
    router.get('/api/newsList', controller.news.getNewsList);
    router.get('/api/newsDetail/:id', controller.news.getNewDetail); // 获取单条文章接口
    router.get('/api/getAllNews', controller.news.findAll); // 带分页接口

    router.get('/api/newsListPage', controller.news.findAllInPage); // 内嵌页面的 没分页
};