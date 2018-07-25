'use strict';

const Controller = require('egg').Controller;

class NewsController extends Controller {
    async getNewsList() {
        const news = await this.ctx.service.news.getNewsList();
        // return { news };
        await this.ctx.render('news/list.tpl', news);
        // this.ctx.body = news;
    }
    async getNewDetail() {
        const id = this.ctx.params.id;
        const neww = await this.ctx.service.news.getNewById(id);
        await this.ctx.render('news/detail.tpl', neww);
        // this.ctx.body = neww;
    }
}

module.exports = NewsController;