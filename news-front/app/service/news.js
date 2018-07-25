module.exports = app => {
    return class NewsService extends app.Service {
        async getNewById(id) {
            const neww = await this.app.mysql.get('news', { id: id });
            if (!neww) {
                return 0;
            } else {
                return { neww };
            }
        }
        async getNewsList() {
            const news = await this.app.mysql.select('news');
            return news;
        }
        async findAll(data) {
            let pageSize = data.pageSize;
            console.log('/////////////////:' + pageSize);
            let page = data.page;
            if (pageSize == null || pageSize == undefined) {
                pageSize = 20;
            }
            if (page == null || page == undefined) {
                page = 1;
            }
            console.log('/////////////////:' + page);
            let start = (page - 1) * data.pageSize;
            const articles = await this.app.mysql.query('select * from news limit ' + start + ',' + pageSize);
            const ta = await this.app.mysql.query('SELECT COUNT(*) FROM news ');
            const totalAmount = ta[0]['COUNT(*)'];
            const totalPage = Math.ceil(totalAmount / pageSize)
            let resulta = {};
            resulta.totalAmount = totalAmount;
            resulta.data = articles;
            resulta.totalPage = totalPage;
            const result = JSON.stringify({ msg: '操作成功', status: '100', totalPage: totalPage, data: articles, totalAmount: totalAmount })
            return result;
        }


    }
}