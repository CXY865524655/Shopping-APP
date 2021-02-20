<template>
    <div id="app">
        <keep-alive v-if="$route.meta.keepAlive">
            <transition name="slide-left">
                <router-view id="view"></router-view>
            </transition>
        </keep-alive>
        <DoubleBounce v-show="LOADING" style="width:100px;height:100px;left:0;right:0;bottom:0;top:0;position: absolute; margin: auto;"></DoubleBounce>
    </div>
</template>

<script>  
import {DoubleBounce} from 'vue-loading-spinner'
import { vuexData } from '@/assets/js/mixin.js'
export default {
  name: 'app',
  mixins: [ vuexData ],
  data() {
    return {
            animate: "",
            aa: 123,
        }
  },
  components: {
    DoubleBounce
  },
  
  created(){
    this.SET_LOADING({
        loading: false
    })
  },
  mounted() {},
  activated(){},
  watch: {
      $route(to, from) {
            let animate = this.$router.animate;
            let tabPages = ["My", "ShoppingCart", "Category", "Home"];
            if (tabPages.includes(to.name) && animate != 2) {
                this.animate = "fade";
            } else {
                this.animate = "slide-left";
            }
            if (animate == 1) {
                this.animate = "slide-right";
            }
            this.$router.animate = 0;
        }
  },
}
</script>

<style scoped>
#app {
    width: 100%;
    height: 100%;
}
.van-tabbar-item--active {
    color: transparent;
}
.fade-enter {
    opacity: 0;
}

.fade-leave {
    opacity: 1;
}

.fade-enter-active {
    transition: opacity 0.3s;
}

.fade-leave-active {
    opacity: 0;
    transition: opacity 0s;
}
#view {
    width: 100%;
    height: 100%;
    transition: all 0.5s cubic-bezier(0.55, 0, 0.1, 1);
}
.slide-left-enter,
.slide-right-leave-active {
    opacity: 0;
    transform: translate(100%, 0);
}
.slide-left-leave-active,
.slide-right-enter {
    opacity: 0;
    transform: translate(-100%, 0);
}
.slide-top-enter,
.slide-bottom-leave-active {
    opacity: 0;
    transform: translate(0, 100%);
}
.slide-top-leave-active,
.slide-bottom-enter {
    opacity: 0;
    transform: translate(0, -100%);
}
</style>
