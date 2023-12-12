import { Pipe, PipeTransform } from "@angular/core";

@Pipe({
    name: 'replace'
})
export class ReplacePipe implements PipeTransform{
    
    transform(value: string, strToRepace: string, replacementStr: string) {
        if(!value || !strToRepace || !replacementStr){
            return value;
        }
        return value.replace(new RegExp(strToRepace, 'g'), replacementStr);
    }

}