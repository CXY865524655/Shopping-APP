<template>
  <div class="tabsss">
    <div class="tabs" ref="tabsRef">
      <ul ref="tab">
        <li v-for="(item, index) in subCategoryList" :key="index" @click.stop="selectItem(index+1)"
         class="subCategory"
         
         >
          <div class="tab-item">
            <span class="tab-link" :class="{selected: actived===(index+1)}" ref="subTab">{{item.subcategoryname}}</span>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import BScroll from 'better-scroll'
import { mapGetters, mapMutations } from 'vuex'
export default {
  props: {
    rightContent: Array,
    rightSubCNum: Number
  },
  data () {
    return {
      tabWidth: 85,
      actived: 1,
      categorySelected: '',
      subCategoryList:[],
    }
  },
  computed:{
    ...mapGetters(['GET_IS_CHOOSE_CATEGORY'])
  },
  mounted () {
    this.$nextTick(() => {
      
      this._initMenu()
    })
  },
  methods: {
    ...mapMutations(['SET_IS_CHOOSE_CATEGORY']),
    async selectItem (index) {
      
      this.actived = index
      this.scroll.scrollToElement(this.$refs.subTab[index-1], 300,-100)  // 选取文字上一层直属
    },
    _initMenu () {
      let tabsWidth = this.tabWidth   // 每个 subCategory 的宽度
      let width = this.rightContent.length * tabsWidth  // 总长度 
      this.$refs.tab.style.width = `${width}px`     // 给 tab 设置长度，不然不能滚动  
        if (!this.scroll) {

          this.scroll = new BScroll(this.$refs.tabsRef, {
            probeType: 3,    // 除了实时派发scroll事件，在swipe的情况下仍然能实时派发scroll事件
              click: true,     // 是否派发click事件，通常判断浏览器派发的click还是betterscroll派发的click，可以用_constructed，若是bs派发的则为true
              scrollX: true,   // true 滚动方向为 Y 轴
              tap: true,   // better-scroll 会阻止原生的 click 事件，我们可以设置 tap 为 true
              mouseWheel: true, // 启动鼠标滚轴
              // eventPassthrough: 'horizontal',
              startX: 0,
          })

        } else {
          this.scroll.refresh()

          // 如果选择了大标签
          if(this.GET_IS_CHOOSE_CATEGORY) {
            this.actived = 1
            this.SET_IS_CHOOSE_CATEGORY({
              choose: false
            })
          }

          // 改动
          if(this.$refs && this.$refs.subTab) {
              this.scroll.scrollToElement(this.$refs.subTab[0],300,-100)  // 切换subCategory时滚动到第一个category并选中
          }
        }
    }
  },
  watch: {

    tabs() {
      this.scroll.refresh()
    },
    actived(newVal) {
      this.$emit('update:rightSubCNum', newVal)
    },
    rightContent(newVal) {
      this.subCategoryList = newVal
      this._initMenu()

    },
    rightSubCNum(newVal) {
      this.actived = newVal
    },
    
  }
}
</script>

<style lang="less" scoped>
  .tabsss {
    position: relative;
    height: 3.15rem;
    width: 100%;
    z-index: 102;
    background-color: white;
    .tabs {
        margin-top: 0rem;
        height: 3.0rem;    // 选中红色下划线
        width: 100%;
        line-height: 3.2rem;
        box-sizing: border-box;
        overflow: hidden;
        white-space: nowrap;
        
        .tab-item {
            float: left;
            width: 80px;
            height: 100%;
            text-align: center;
            // border-bottom-color: red;
            // border-bottom: 1px solid red;
            font-size: 0.81rem;
            .tab-link {
                // height: 100px;
                padding-bottom: 8px;
            }
            .tab-link.selected {
                color: #d33a31;
                border-bottom: 2px solid #d33a31;
                box-sizing: border-box;
            }
            
        }
    }
  }

</style>