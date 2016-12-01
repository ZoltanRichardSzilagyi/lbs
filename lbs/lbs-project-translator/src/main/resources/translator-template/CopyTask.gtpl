task ${task.name}(type: Copy) {
	from '${task.from}'
	into '${task.into}'
	include('${task.include}')

}