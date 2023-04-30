import {Image} from "./image";

export interface Cart {
  name:string;
  id:number;
  amount:number;
  price:number;
  imageList:Image[];
}
