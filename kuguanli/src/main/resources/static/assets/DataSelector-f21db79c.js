import{_ as c,r as d,o as l,c as n,a as p,w as i,F as m,h as _,b as v}from"./index-402981fb.js";const f={emits:["update:selected"],props:{selected:{type:Number,required:!0},data:{type:Array,default:[]},placeholder:{type:String,required:!0}},data(){return{value:""}},computed:{value:{get(){return this.value},set(t){this.value=t,this.$emit("update:selected",t)}}}};function h(t,a,r,y,b,o){const s=d("el-option"),u=d("el-select");return l(),n("div",null,[p(u,{modelValue:o.value,"onUpdate:modelValue":a[0]||(a[0]=e=>o.value=e),filterable:"","reserve-keyword":"",placeholder:r.placeholder},{default:i(()=>[(l(!0),n(m,null,_(r.data,e=>(l(),v(s,{key:e.id,label:e.name,value:e.id},null,8,["label","value"]))),128))]),_:1},8,["modelValue","placeholder"])])}const x=c(f,[["render",h]]);export{x as D};
