import {Image} from "./image";

export interface ProductDTO {
  id?:number;
  name?:string;
  price?:number;
  description?:string;
  quantity?:number;
  dateSubmitted?:string;
  image?:Image

}
