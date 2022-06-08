var grid = new tui.Grid( {
			el: document.getElementById('grid'),
			columns: [
			{
			      header: '분야',
			      name: 'field',
			      align: 'center'
			    },
			    {
			      header: '경력',
			      name: 'career',
			      editor: 'text'
			    },
			    {
			      header: '나이',
			      name: 'age'
			    },
			    {
			      header: '희망연봉',
			      name: 'salary_desired'
			    }
			  ],
		} );

        // GRID 에 데이터를 입력한다.
		var arrData = [
			 {
			     field:'자바스크립트',
			     career:'7년',
			     age:'12세',
			     salary_desired:'1억'
			  },
			  {
			     field:'파이썬',
			     career:'3년',
			     age:'9세',
			     salary_desired:'2억'
			  },
			  {
			     field:'자바',
			     career:'9년',
			     age:'15세',
			     salary_desired:'3억'
			  },
			  {
			     field:'A.I',
			     career:'10년',
			     age:'17세',
			     salary_desired:'4억'
			  }
		];

		grid.resetData( arrData );
