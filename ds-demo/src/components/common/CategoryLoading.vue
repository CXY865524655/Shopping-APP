<template>
  <div class="loading"><img src="@/assets/images/loading/tail.gif"  alt="">
    <span>{{text}}{{loadingWord}}</span>
  </div>
</template>

<script>
    export default {
        name: "loading",
        props:['loadingWord','text']
    }
</script>

<style lang="scss" scoped>
  // @import "../../assets/scss/base/function";
  .loading {
    text-align: center;
    z-index: 100;
    width: 100%;
    height: 20px;
    line-height: 20px;
    margin-top: 5px;
    color:#959ba3 ;
    img {
      margin: 0 auto;
      width: 20px;
      height: 20px;
      vertical-align: middle;
    }
    span {
      vertical-align: middle;
      margin-left: 15px;
      font-size: 15px;
    }
  }
</style>
