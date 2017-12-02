import { Pipe, PipeTransform } from '@angular/core';


@Pipe({ name: 'Capitalise' })
export class ExamplePipe implements PipeTransform {
  transform(value: string) {
    return value.toUpperCase();
  }
}
