'use strict';

const Controller = require('egg').Controller;

class NewsController extends Controller {
    async getNewsList() {
        const news = await this.ctx.service.news.getNewsList();
        // return { news };
        this.ctx.body = news;
    }
    async getNewDetail() {
        const id = this.ctx.params.id;
        const neww = await this.ctx.service.news.getNewById(id);
        this.ctx.body = neww;
        // return { neww };
    }
}

module.exports = NewsController;