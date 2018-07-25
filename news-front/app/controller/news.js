'use strict';

const Controller = require('egg').Controller;

class NewsController extends Controller {
    async findAll() {
        const query = this.ctx.query;
        const news = await this.ctx.service.news.findAll(query);
        this.ctx.body = news;
    }
    async getNewsList() {
        const news = await this.ctx.service.news.getNewsList();
        // await this.ctx.render('news/list.tpl', { news });
        this.ctx.body = news;
    }
    async findAllInPage() {
        // const news = await this.ctx.service.news.getNewsList();
        const query = this.ctx.query;
        const news = await this.ctx.service.news.findAll(query);
        await this.ctx.render('news/list.tpl', { areaslist: news });
    }
    async getNewDetail() {
        const id = this.ctx.params.id;
        const neww = await this.ctx.service.news.getNewById(id);
        // await this.ctx.render('news/detail.tpl', neww);
        this.ctx.body = neww;
    }
}

module.exports = NewsController;