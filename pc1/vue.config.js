const path = require('path');

function resolve(dir) {
    return path.join(__dirname, dir);
}

module.exports = {
    lintOnSave: true,
    publicPath: './',     // update   原来项目 + 的

    chainWebpack: (config) => {
        config.resolve.alias
            .set('@', resolve('src'))
        
        // svg-icon 配置  
        config.module
            .rule('svg')
            .exclude.add(resolve('src/icons'))
            .end()
        config.module
            .rule('icons')
            .test(/\.svg$/)
            .include.add(resolve('src/icons'))
            .end()
            .use('svg-sprite-loader')
            .loader('svg-sprite-loader')
            .options({
                symbolId: 'icon-[name]'
            })
            .end()   // end
        
    },
    pluginOptions: {
        'style-resources-loader': {
            preProcessor: 'scss',
            patterns: [
                path.resolve(__dirname, 'src/style/_variables.scss')
            ]
        }
    },
    devServer: {
        port: 8088,
        open: true, // 是否自动打开浏览器页面
        host: 'localhost',
        https: false,

        // 如果打包成 APP ,就把下面的注释掉
        proxy: {
            '/api': {
                target: 'http://localhost:8093',  //'http://localhost:8093  本地     http://47.112.190.111:8093    阿里云'
                ws: true,
                changeOrigin: true,  //是否跨域
                pathRewrite: {
                    '^/api': ''
                }
            }
        }
    },


    
}
